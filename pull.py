#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
from multiprocessing.dummy import Pool as ThreadPool 
import argparse
import requests
import itertools
import subprocess
import os
import string

def get_args():
    parser = argparse.ArgumentParser(description='crawl Lightshot - poc')
    parser.add_argument('-n', '--number', required=False, default=6, action='store', help='Number of char in url')
    parser.add_argument('-u', '--url', required=False, default='prnt.sc', action='store', help='Base url')
    parser.add_argument('-l', '--limit', required=False, default=10, action='store', help='limit image to retreive')
    parser.add_argument('-s', '--start', required=False, default=0, action='store', help='start at number')
    args = parser.parse_args()
    return args

def writedata(filename, data):
    with open(filename, 'wb') as f:
        data = f.write(data)

def extractdata(get_url):
    headers = {'Content-Type':'text/html; charset=UTF-8', 'User-Agent':'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0'}
    response = requests.get(get_url, headers=headers)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, 'lxml')
        img = soup.find("div", {"class":"image-container image__pic js-image-pic"}).find("img")
        if img['src'] and img['image-id'] and not (img['src'].startswith('//')):
            image_name = img['src'].split('/')[-1]
            origin_image = requests.get(img['src'], headers=headers)
            writedata(image_name, origin_image.content)
            print(image_name)
            try:
                result = subprocess.check_output(['gocr', image_name])
                ocr_result = result
                writedata(image_name+'.meta', ocr_result)
            except subprocess.CalledProcessError as e:
                pass

def main(args):
    loop = 0
    pool = ThreadPool(8)
    urls = []
    charset = string.ascii_lowercase + string.digits
    params = itertools.product(charset, repeat=int(args.number))
    for param in params:
        loop = loop + 1
        if int(loop) <= int(args.start):
            continue
        get_url = str(args.url) + str(''.join(param))
        urls.append(get_url)
        if int(loop) == int(args.limit)+int(args.start):
            break
    pool.map(extractdata, urls)
    pool.close() 
    pool.join()

# Start
if __name__ == "__main__":
    args = get_args()
    main(args)
