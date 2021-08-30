# nosetests

> ${NOSETESTS}  -s -v   ${rdCaseName}   --with-html-output --html-out-file=all.html
> ${rdCaseName}  可以使用文件名的前缀加*匹配所有测试文件

指定具体单个case执行 文件：类名.方法名

nosetests -s test_report.py:TestReport.test_case04
