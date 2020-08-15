from bs4 import BeautifulSoup
import cloudscraper
import os
from itertools import combinations
import time
import threading
from random import randint
import logging

os.makedirs(os.path.dirname("img/"), exist_ok=True)
os.makedirs(os.path.dirname("logs/"), exist_ok=True)

scraper = cloudscraper.create_scraper()
list_id = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9']
endpoint = "https://prnt.sc/"




def setup_logger():
    # Prints logger info to terminal
    logger = logging.getLogger()
    logger.setLevel(logging.INFO)  # Change  this to DEBUG if you want a lot more info
    stream_handler = logging.StreamHandler()
    file_handler = logging.FileHandler("logs/scrapper.log", encoding="utf-8")
    # http_handler = HTTPHandler(host="localhost:8086", url="/write?db=serveur-ressources", method="POST")
    # create formatter
    formatter = logging.Formatter(
    "%(asctime)s - %(levelname)s - %(message)s - line %(lineno)d")

    # add formatter to stream_handler
    stream_handler.setFormatter(formatter)
    file_handler.setFormatter(formatter)
    # http_handler.setFormatter(formatter_http)

    logger.addHandler(file_handler)
    logger.addHandler(stream_handler)
    # logger.addHandler(http_handler)

    return logger



def rSubset(arr, r): 

    return list(combinations(arr, r)) 
  

def get_img_url(id_img, logger):
    page = scraper.get(endpoint+id_img)
    soup = BeautifulSoup(page.content, "lxml")

    url_img = soup.find(id="screenshot-image").get("src")

    if "//st.prntscr.com" in url_img:
        logger.info("Image was removed")
        return 404

    logger.info("Get {} from {}".format(url_img, endpoint+id_img))

    return url_img

def get_img(url_img, id_img):
    img = scraper.get(url_img)

    with open("img/"+id_img+".png", "wb") as f:
        f.write(img.content)

def run(id_img, logger):
    logger.info("[*] id : {}".format(id_img) )
    url_img = get_img_url(id_img, logger)

    if url_img == 404:
        pass
    else:
        get_img(url_img, id_img)


if __name__ == "__main__":
        logger = setup_logger()
        list_combi = rSubset(list_id, 6) # generate all combinaisons with 6 characters
        logger.info("List of combinaisons generated")

        list_already_get = os.listdir("img/")

        for combi in list_combi:
            id_img = "".join(combi)
            if id_img+".png" not in list_already_get:
                threading.Thread(target=run, args=[id_img, logger]).start()
                time.sleep(randint(0, 1))
            else:
                logger.info("{} skipped (already have)".format(id_img))

            
