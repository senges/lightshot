# Lightshot Filename Path Disclosure (POC)

This proof of concept shows how lightshot screenshot hosting service can be easily crawled without any restriction.  
This weakness was first discovered by Naïm GALLOUJ.  
Script Author : Charles SENGES (me, btw).

## Usage

`$  ./pull.sh <url> <number of level>`

* URL : Your startig point
* Levels : How much you want to crawl the url. (See exemples)

## Exemples

`$  ./pull.sh https://prnt.sc/abc123 1`  
  
Will go from "https://prnt.sc/abc120" to "https://prnt.sc/abc12z"  
Could also be seen as "https://prnt.sc/abc12*"  
  
In the same way :  
  
`$  ./pull.sh https://prnt.sc/abc123 6`

Could be seend as "https://prnt.sc/******"
The script would then crawl the whole website (could be long if you don't have a quantum computer btw (I know quantum computer wouldn't help but.. come on))  
