package com.gency.subscribe.core.plug.generator;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
/**
 * 
 * 作者:唐鹏
 * 描述:	自定义mybatis插件
 * 版本: version 1.0.0
 * 时间: 2017年7月17日 下午3:41:00
 */
public class GenPlugin extends PluginAdapter{
	
	private Set<String> mappers = new HashSet<String>();
	// 注释生成器
	private CommentGeneratorConfiguration commentCfg;
	
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
	@Override
	public void setContext(Context context) {
		super.setContext(context);
		// 设置默认的注释生成器
		commentCfg = new CommentGeneratorConfiguration();
		commentCfg.setConfigurationType(BDTCommentGenerator.class.getCanonicalName());
		context.setCommentGeneratorConfiguration(commentCfg);
		context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String mappers = this.properties.getProperty("mappers");
		/**
		 * mapper父类封装
		 */
		for (String mapper : mappers.split(",")) {
			this.mappers.add(mapper);
		}
	}

	/**
	 * 生成的Mapper接口
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// 获取实体类
		FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		// import接口
		for (String mapper : mappers) {
			interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
			interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
		}
		// import实体类
		interfaze.addImportedType(entityType);
		return true;
	}

	/**
	 * 拼装SQL语句生成Mapper接口映射文件
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement rootElement = document.getRootElement();
		// 数据库表名
		String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
		// 主键
		IntrospectedColumn pkColumn = introspectedTable.getPrimaryKeyColumns().get(0);

		// 公共字段
		StringBuilder columnSQL = new StringBuilder();
		// IF判断语句(默认加上条件查询未删除的结果)
		StringBuilder ifSQL = new StringBuilder();
		// 要插入的字段(排除自增主键)
		StringBuilder saveColumn = new StringBuilder("insert into ").append(tableName).append("(\n");
		// 要保存的值
		StringBuilder saveValue = new StringBuilder("(\n");
		// 拼装更新字段
		StringBuilder updateSQL = new StringBuilder("update ").append(tableName).append("\n <set> \n");

		// 数据库字段名
		String columnName = null;
		// java字段名
		String javaProperty = null;
		for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			columnName = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);
			javaProperty = introspectedColumn.getJavaProperty();
			// 拼接字段
			columnSQL.append(columnName).append(",");
			// 拼接IF语句
			//判断如果是int类型的字段只判断当前值是否为空
			if(introspectedColumn.getJdbcTypeName().equals("INTEGER")){
				ifSQL.append("      <if test=\"null != item.").append(javaProperty).append("\">");
			}else{
				ifSQL.append("      <if test=\"null != item.").append(javaProperty).append(" and '' != item.").append(javaProperty).append("\">");
			}
			ifSQL.append("and ").append(columnName).append(" = #{item.").append(javaProperty).append("}</if>\n");

			// 拼接SQL
			if (!introspectedColumn.isAutoIncrement()) {
				saveColumn.append("\t  <if test=\"null != item.").append(javaProperty).append("\">, ").append(columnName).append("</if>\n");

				saveValue.append("\t  <if test=\"null != item.").append(javaProperty).append("\">, ").append("#{item.").append(javaProperty)
						.append("}</if>\n");

				// 时间格式用now()作为值
				 /* if(Types.TIMESTAMP == introspectedColumn.getJdbcType()){
				  saveValue.append(", now()"); }else{ saveValue.append(
				  ", #{item.").append(javaProperty).append("}"); }*/
				

				updateSQL.append("      <if test=\"null != item.").append(javaProperty).append("\">");
				updateSQL.append(columnName).append(" = #{item.").append(javaProperty).append("},</if>\n");
			}
		}
		updateSQL.append("</set>");
		String columns = columnSQL.substring(0, columnSQL.length() - 1);
		rootElement.addElement(createSql("sql_columns", columns));

		String whereSQL = MessageFormat.format("<if test=\"item !=null \"> \n <where>\n{0}\t</where> \n </if>", ifSQL.toString());
		rootElement.addElement(createSql("sql_where", whereSQL));

		rootElement.addElement(createSelect("selectById", tableName, pkColumn,pkColumn.toString()));
		rootElement.addElement(createSelect("selectOne", tableName, pkColumn,pkColumn.toString()));
		rootElement.addElement(createSelect("selectList", tableName, pkColumn,pkColumn.toString()));
		//rootElement.addElement(createSelect("selectListNoCondition", tableName, pkColumn,pkColumn.toString()));
		rootElement.addElement(createSql("sql_save_columns", saveColumn.append("\t) values").toString().replaceFirst(",", "")));
		rootElement.addElement(createSql("sql_save_values", saveValue.append("\t)").toString().replaceFirst(",", "")));
		rootElement.addElement(createSave("save", pkColumn));
		rootElement.addElement(createSave("batchSave", null));

		updateSQL.append("\twhere ").append(pkColumn.getActualColumnName()).append(" = #{item.").append(pkColumn.getJavaProperty()).append("}");
		rootElement.addElement(createSql("sql_update", updateSQL.toString()));

		rootElement.addElement(createUpdate("update"));
		rootElement.addElement(createUpdate("batchUpdate"));

		rootElement.addElement(createDels(tableName, pkColumn, "delArray", "array"));
		rootElement.addElement(createDels(tableName, pkColumn, "delList", "list"));
		rootElement.addElement(createDels(tableName, pkColumn, "delByCondition", null));
		rootElement.addElement(createDels(tableName, pkColumn, "delById", null));
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	/**
	 * 公共SQL
	 * @param id
	 * @param sqlStr
	 * @return
	 */
	private XmlElement createSql(String id, String sqlStr) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", id));
		sql.addElement(new TextElement(sqlStr));
		return sql;
	}

	/**
	 * 查询
	 * @param id
	 * @param tableName
	 * @param pkColumn
	 * @return
	 */
	private XmlElement createSelect(String id, String tableName, IntrospectedColumn pkColumn,String pkName) {
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", id));
		select.addAttribute(new Attribute("resultMap", "BaseResultMap"));

		StringBuilder selectStr = new StringBuilder("select <include refid=\"sql_columns\" /> from ").append(tableName);
		if(id.equals("selectListNoCondition")){
		}
		else if (id.equals("selectById")) {
			selectStr.append(" where ").append(pkColumn.getActualColumnName()).append(" = #{").append(pkColumn.getJavaProperty()).append("}"
					+ " and isDelete=0 ");
		} else {
			selectStr.append(" <include refid=\"sql_where\" />");
		}
		if(id.equals("selectList")||id.equals("selectListNoCondition")){
			/*排序*/
			selectStr.append("  order by "+pkColumn.getJavaProperty()+" desc");
		}
		/*增加只查询一条sql拼接*/
		if(id!=null&&id.equals("selectOne")){
			selectStr.append(" limit 1");
		}
		select.addElement(new TextElement(selectStr.toString()));
		return select;
	}

	/**
	 * 保存
	 * @param id
	 * @param pkColumn
	 * @return
	 */
	private XmlElement createSave(String id, IntrospectedColumn pkColumn) {
		XmlElement save = new XmlElement("insert");
		save.addAttribute(new Attribute("id", id));
		if (null != pkColumn) {
			save.addAttribute(new Attribute("keyProperty", "item." + pkColumn.getJavaProperty()));
			save.addAttribute(new Attribute("useGeneratedKeys", "true"));
			save.addElement(new TextElement("<include refid=\"sql_save_columns\" /><include refid=\"sql_save_values\" />"));
		} else {
			StringBuilder saveStr = new StringBuilder(
					"<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" close=\"\">\n\t  ")
							.append("<include refid=\"sql_save_columns\" /><include refid=\"sql_save_values\" />\n\t</foreach>");
			save.addElement(new TextElement(saveStr.toString()));
		}
		return save;
	}

	/**
	 * 更新
	 * @param id
	 * @return
	 */
	private XmlElement createUpdate(String id) {
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", id));
		if ("update".equals(id)) {
			update.addElement(new TextElement("<include refid=\"sql_update\" />"));
		} else {
			update.addElement(new TextElement(
					"<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" close=\"\">\n\t  <include refid=\"sql_update\" />\n\t</foreach>"));
		}
		return update;
	}
	private XmlElement createDels(String tableName, IntrospectedColumn pkColumn, String method, String type) {
		XmlElement delete = new XmlElement("delete");
		delete.addAttribute(new Attribute("id", method));
		//根据条件进行删除isDelete
		StringBuilder deleteStr=new StringBuilder();
		if(method.equals("delByCondition")){
			 deleteStr.append(" update ").append(tableName).append(" set isDelete=1 ").append("<include refid=\"sql_where\" />");
		}else if(method.equals("delById")){
			 deleteStr.append(" update ").append(tableName).append(" set isDelete=1 ").append(" where ").append(pkColumn.getActualColumnName()).append(" =#{id} ");
		}
		else{
			deleteStr.append("delete from ").append(tableName).append(" where ").append(pkColumn.getActualColumnName())
					.append(" in\n\t")
					.append("<foreach collection=\"").append(type)
					.append("\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>");
		
		}
		delete.addElement(new TextElement(deleteStr.toString()));
		return delete;
	}
   /**
    * 
    * 作者:唐鹏
    * 描述:自定义删除方法 将isDelete字段值默认更改为1
    * 版本: version 1.0.0
    * 时间: 2017年7月17日 下午4:15:17
    * @param tableName
    * @param pkColumn
    * @param method
    * @param type
    * @return
    * XmlElement
    */
	/*private XmlElement createDels(String tableName, IntrospectedColumn pkColumn, String method, String type) {
		XmlElement delete = new XmlElement("update");
		delete.addAttribute(new Attribute("id", method));
		StringBuilder deleteStr = new StringBuilder("update ").append(tableName).append(" set  isDelete=1 where ").append(pkColumn.getActualColumnName())
				.append(" in\n\t")
				.append("<foreach collection=\"").append(type)
				.append("\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>");
		delete.addElement(new TextElement(deleteStr.toString()));
		return delete;
	}*/
	 @Override  
     public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {  
	        addSerialVersionUID(topLevelClass, introspectedTable);
	        topLevelClass.addImportedType("com.gency.subscribe.model.base.BaseModel");
	        topLevelClass.addJavaDocLine("/**");
	        topLevelClass.addJavaDocLine("	* 作者:唐鹏");
	        topLevelClass.addJavaDocLine("	* 描述:");
	        topLevelClass.addJavaDocLine("	* 版本: version 1.0.0");
	        topLevelClass.addJavaDocLine("	* 时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        topLevelClass.addJavaDocLine("  */");
	        /* List<String> warnings = new ArrayList<String>(); 
	        Context conts=new Context(ModelType.CONDITIONAL);
	        //conts.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
	        JDBCConnectionConfiguration jdbcConfg=new JDBCConnectionConfiguration();
	        jdbcConfg.setConnectionURL("jdbc:mysql://192.168.0.17/ecdb3?characterEncoding=utf8");
	        jdbcConfg.setDriverClass("com.mysql.jdbc.Driver");
	        jdbcConfg.setPassword("74k2iVPO");
	        jdbcConfg.setUserId("hpf");
	        conts.setJdbcConnectionConfiguration(jdbcConfg);
	        TableConfiguration table =new TableConfiguration(conts);
	        table.setTableName("manage_role_authority");
	        table.setDomainObjectName("ManageRoleAuthority");
	        conts.addTableConfiguration(table);
	        conts.getIntrospectedTables();
	        try {
        	AbstractJavaGenerator javaGenerator = new SimpleModelGenerator();
        	//读取数据库生成对应的 tablecomle
	        conts.introspectTables(new NullProgressCallback(), warnings, null);
	        //conts.
	        //javaGenerator.setContext(conts);
	        //javaGenerator.
	        //System.out.println(conts.getIntrospectionSteps());
	        IntrospectedTable tablsesd= javaGenerator.getIntrospectedTable();
	        System.out.println(tablsesd.getTableType());
	        //List<IntrospectedTable> introspectedTables=conts.getCommentGenerator().
	        //IntrospectedColumn introspectedColumn=ObjectFactory.createIntrospectedColumn(conts);
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        
	        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);  
	    }  
	
	// 以下设置为false,取消生成默认增删查改xml
	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	/**
	 * 
	 * 作者:唐鹏
	 * 描述: model文件中生成唯一编号
	 * 版本: version 1.0.0
	 * 时间: 2017年7月17日 下午4:17:31
	 * @param topLevelClass
	 * @param introspectedTable
	 * void
	 */
	 private void addSerialVersionUID(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {  
	        CommentGenerator commentGenerator = context.getCommentGenerator();  
	        Field field = new Field();  
	        field.setVisibility(JavaVisibility.PRIVATE);  
	        field.setType(new FullyQualifiedJavaType("long"));  
	        field.setStatic(true);  
	        field.setFinal(true);  
	        field.setName("serialVersionUID");  
	        field.setInitializationString("1L");  
	        commentGenerator.addFieldComment(field, introspectedTable);  
	        topLevelClass.addField(field);  
	    }  
}
