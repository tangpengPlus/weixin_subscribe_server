 <!DOCTYPE html>
<html lang="en">
  <head>
 [#include "/base/base.ftl"/]
  <script src="${base}/js/jquery.js"></script>
    <script src="${base}/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="${base}/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="${base}/js/jquery.scrollTo.min.js"></script>
    <script src="${base}/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="${base}/js/respond.min.js" ></script>
    <script src="${base}/js/common-scripts.js"></script>
  <script src="js/common-scripts.js"></script>
	<script type="text/javascript" src="${base}/assets/bootstrap-datepicker/js/bootstrap-datepicker.js" len=""></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
  </head>
  <body>
    <div class="row">
      <div class="col-lg-12">
          <section class="panel">
              <header class="panel-heading">
                  <form class="form-inline" role="form">
                      <div class="form-group">
                          <label class="sr-only" for="exampleInputEmail2">Email address</label>
                          <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
                      </div>
                      <div class="form-group">
                          <label class="sr-only" for="exampleInputPassword2">Password</label>
                          <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
                      </div>
					  <div class="form-group">
						  <div data-date="2013/07/13" data-date-format="yyyy/mm/dd">
                              <input type="text" class="form-control from default-date-picker" name="from" readonly="" placeholder="开始时间">
							  
                          </div>
						  
					  </div>
					  <span>To</span>
					<div class="form-group">
						  <div data-date="2013/07/13" data-date-format="yyyy/mm/dd">
                              <input type="text" class="form-control to default-date-picker" name="to" readonly="" placeholder="结束时间">
                          </div>
					  </div>								  
                      <button type="submit" class="btn btn-success">查询</button>
                  </form>
              </header>
              <div class="panel-body">
                  <section id="no-more-tables">
                      <table class="table table-bordered table-striped table-condensed cf">
                          <thead class="cf">
                          <tr>
                              <th>Code</th>
                              <th>Company</th>
                              <th class="numeric">Price</th>
                              <th class="numeric">Change</th>
                              <th class="numeric">Change %</th>
                              <th class="numeric">Open</th>
                              <th class="numeric">High</th>
                              <th class="numeric">Low</th>
                              <th class="numeric">Volume</th>
                          </tr>
                          </thead>
                          <tbody>
                          <tr>
                              <td data-title="Code">AAC</td>
                              <td data-title="Company">AUSTRALIAN AGRICULTURAL COMPANY LIMITED.</td>
                              <td class="numeric" data-title="Price">$1.38</td>
                              <td class="numeric" data-title="Change">-0.01</td>
                              <td class="numeric" data-title="Change %">-0.36%</td>
                              <td class="numeric" data-title="Open">$1.39</td>
                              <td class="numeric" data-title="High">$1.39</td>
                              <td class="numeric" data-title="Low">$1.38</td>
                              <td class="numeric" data-title="Volume">9,395</td>
                          </tr>
                          </tbody>
                      </table>
                  </section>
              </div>
          </section>
      </div>
  </div>
  </body>
</html>
          