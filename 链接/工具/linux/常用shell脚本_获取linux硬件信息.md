# 常用shell脚本

## mem

> cat /proc/meminfo | grep -E "^(MemFree|Cached|Buffers|MemTotal)" | awk '{if($1=="MemFree:"||$1=="Cached:"||$1=="Buffers:"){free+=$2}if($1=="MemTotal:"){total=$2}printf("mem_%s%s\n",$1,$2)}END{printf("mem_used_percent:%.4f\n",100-(total-free)/total*100)}'

## disk

> df -k | grep -E "/storage$|/tmp$|/$" |  awk '{split($5,a,"%");p=a[1];printf("disk_total_@%s@%s:%s\ndisk_used_@%s@%s:%s\ndisk_ava_@%s@%s:%s\ndisk_used_percent_@%s@%s:%s\n",$1,$6,$2,$1,$6,$3,$1,$6,$4,$1,$6,p)}'

## cpu

> cat /proc/stat | grep cpu | awk '{idle=$5;sum=0;for(i=1;i<=NF;i++){sum+=$i};printf("cpuidle_@%s:%.4f\n",$1, 100*idle/sum)}'

## io

> iostat -k|grep -A50 Device | grep -Ev "^$" | grep -v Device | awk '{printf("io_read_@%s:%s\nio_write_@%s:%s\n",$1,$3,$1,$4)}'
