# curl的部分时间等变量注释

···
url_effective The URL that was fetched last. This is most meaningful if you've told curl to follow location: headers.

filename_effective The ultimate filename that curl writes out to. This is only meaningful if curl is told to write to a file with the --remote-name or --output option. It's most useful in combination with the --remote-header-name option. (Added in 7.25.1)

http_code http状态码，如200成功,301转向,404未找到,500服务器错误等。(The numerical response code that was found in the last retrieved HTTP(S) or FTP(s) transfer. In 7.18.2 the alias response_code was added to show the same info.)

http_connect The numerical code that was found in the last response (from a proxy) to a curl CONNECT request. (Added in 7.12.4)

time_total 总时间，按秒计。精确到小数点后三位。 （The total time, in seconds, that the full operation lasted. The time will be displayed with millisecond resolution.）

time_namelookup DNS解析时间,从请求开始到DNS解析完毕所用时间。(The time, in seconds, it took from the start until the name resolving was completed.)

time_connect 连接时间,从开始到建立TCP连接完成所用时间,包括前边DNS解析时间，如果需要单纯的得到连接时间，用这个time_connect时间减去前边time_namelookup时间。以下同理，不再赘述。(The time, in seconds, it took from the start until the TCP connect to the remote host (or proxy) was completed.)

time_appconnect 连接建立完成时间，如SSL/SSH等建立连接或者完成三次握手时间。(The time, in seconds, it took from the start until the SSL/SSH/etc connect/handshake to the remote host was completed. (Added in 7.19.0))

time_pretransfer 从开始到准备传输的时间。(The time, in seconds, it took from the start until the file transfer was just about to begin. This includes all pre-transfer commands and negotiations that are specific to the particular protocol(s) involved.)

time_redirect 重定向时间，包括到最后一次传输前的几次重定向的DNS解析，连接，预传输，传输时间。(The time, in seconds, it took for all redirection steps include name lookup, connect, pretransfer and transfer before the final transaction was started. time_redirect shows the complete execution time for multiple redirections. (Added in 7.12.3))

time_starttransfer 开始传输时间。在client发出请求之后，Web 服务器返回数据的第一个字节所用的时间(The time, in seconds, it took from the start until the first byte was just about to be transferred. This includes time_pretransfer and also the time the server needed to calculate the result.)

size_download 下载大小。(The total amount of bytes that were downloaded.)

size_upload 上传大小。(The total amount of bytes that were uploaded.)

size_header  下载的header的大小(The total amount of bytes of the downloaded headers.)

size_request 请求的大小。(The total amount of bytes that were sent in the HTTP request.)

speed_download 下载速度，单位-字节每秒。(The average download speed that curl measured for the complete download. Bytes per second.)

speed_upload 上传速度,单位-字节每秒。(The average upload speed that curl measured for the complete upload. Bytes per second.)

content_type 就是content-Type，不用多说了，这是一个访问我博客首页返回的结果示例(text/html; charset=UTF-8)；(The Content-Type of the requested document, if there was any.)

num_connects Number of new connects made in the recent transfer. (Added in 7.12.3)

num_redirects Number of redirects that were followed in the request. (Added in 7.12.3)

redirect_url When a HTTP request was made without -L to follow redirects, this variable will show the actual URL a redirect would take you to. (Added in 7.18.2)

ftp_entry_path The initial path libcurl ended up in when logging on to the remote FTP server. (Added in 7.15.4)

ssl_verify_result ssl认证结果，返回0表示认证成功。( The result of the SSL peer certificate verification that was requested. 0 means the verification was successful. (Added in 7.19.0))

···

···
1、可以直接访问使用：

···

···
#curl -o /dev/null -s -w %{http_code}:%{http_connect}:%{content_type}:%{time_namelookup}:%{time_redirect}:%{time_pretransfer}:%{time_connect}:%{time_starttransfer}:%{time_total}:%{speed_download} www.baidu.com

···

···
输出变量需要按照%{variable_name}的格式，如果需要输出%，double一下即可，即%%，同时，\n是换行，\r是回车，\t是TAB。 


-w 指定格式化文件

-o 请求重定向到,不带此参数则控制台输出返回结果

-s 静默，不显示进度

···

···
2、也可以定义时间格式化文件访问

···

···
#vim  curl-time.txt 
\n
    	      http: %{http_code}\n
               dns: %{time_namelookup}s\n
          redirect: %{time_redirect}s\n
      time_connect: %{time_connect}s\n
   time_appconnect: %{time_appconnect}s\n
  time_pretransfer: %{time_pretransfer}s\n
time_starttransfer: %{time_starttransfer}s\n
     size_download: %{size_download}bytes\n
    speed_download: %{speed_download}B/s\n
                  ----------\n
        time_total: %{time_total}s\n
\n
#curl -w "@curl_time.txt"  -s  -H "Content-Type: application/json" --insecure --header 'Host: passport.500.com' --data '{"platform":"android","userimei":"F5D815EA2BD8DBARD","app_channel":"10000","mbimei":"9DB358AF","version":"3.1.4","username":"hqzx","userpass":"976af4"}' --compressed https://119.147.113.177/user/login

···

···
3、curl以post请求方法

···

···
1）以json格式数据


···

···
#curl -H "Content-Type: application/json" -X POST  --data '{"data":"1"}'  http://127.0.0.1/

···

···
2）以&连接参数数据


#curl -d "data=7778a8143f111272&score=19&app_key=8d49f16fe034b98b&_test_user=test01" "http://127.0.0.1"
其中-F 为带文件的形式发送post请求，   blob为文本框中的name元素对应的属性值。<type="text" >
#curl  -F "blob=@card.txt;type=text/plain"  "http://172.16.102.208:8089/wiapi/score?leaderboard_id=7778a8143f111272&score=40&app_key=8d49f16fe034b98b&_test_user=test01" 

···