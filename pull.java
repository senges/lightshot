	
	/*
	 * @author Squ3D aka Naïm
	 * make sure to include Jsoup.Jar in order to run the Script.
	 *
	 * Using Eclipse :
	 * Right click the Java project -> "Build Path" ->Select add external archive, and upload throw the File manager the Jsoup.jar
	 * 
	 */

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.PrintWriter;
	import java.net.URL;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.HashMap;
	import java.util.Scanner;

	import org.jsoup.*;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;


public class pull {
		
		public static void main (String args []) throws IOException {
		
			
			 char c[] =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
					String[] test = new String[30];	
					int a = 0;
					Scanner sc = new Scanner(System.in);

		            System.out.println("====LighShot dumper tool====");		
		            System.out.println("Enter the Path where you wish to Save the Screenshots you dump");
		            System.out.println("Make sure that it's using this Format : C:/Users/Naïm/Desktop/doci/");
		            System.out.println("Enter it here :");
		            String userpass = sc.nextLine();
		            int imgCount = 0;
					try {
			
						
						//level 1
				                
					 for (int i = 0;i<=30;i++) {
						
						 
						 
						 
				                Document doc = Jsoup.connect("https://prnt.sc/pf677"+c[i]).get();
				                //We noticed with C.Senges that most of the pictures were uploaded to Imgu.r.
				                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").not("img[src*=//st.prntscr.com/2019/09/03/1652/img/footer-logo.png]");
				                for (Element image : images) {
			                        
				                    System.out.println("\n" + image.attr("src"));
				                    try(InputStream in = new URL(image.attr("src")).openStream()){
				                    	//edit the FilePath where you want the picture to be stored.
				                        Files.copy(in, Paths.get(userpass+imgCount+".png"));
				                        imgCount++;
				                    }
				                    
				                   
				                                            
				                }
			
				         
						 
		}
					 
					 //level 2
					 for (int i = 0;i<=30;i++) {
						 imgCount++;
						 
						 
						 
			                Document doc = Jsoup.connect("https://prnt.sc/pf67"+c[i]+c[i]).get();
			                //We noticed with C.Senges that most of the pictures were uploaded to Imgu.r.
			                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").not("img[src*=//st.prntscr.com/2019/09/03/1652/img/footer-logo.png]");
			                for (Element image : images) {
		                        
			                    System.out.println("\n" + image.attr("src"));
			                    try(InputStream in = new URL(image.attr("src")).openStream()){
			                    	//edit the FilePath where you want the picture to be stored.
			                        Files.copy(in, Paths.get(userpass+imgCount+".png"));
			                    }
			                    
			                   
			                                            
			                }
		
			         
					 
	}
					 //level 3
					 for (int i = 0;i<=30;i++) {
							
						 imgCount++;
						 
						 
			                Document doc = Jsoup.connect("https://prnt.sc/pf6"+c[i]+c[i]+c[i]).get();
			                //We noticed with C.Senges that most of the pictures were uploaded to Imgu.r.
			                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").not("img[src*=//st.prntscr.com/2019/09/03/1652/img/footer-logo.png]");
			                for (Element image : images) {
		                        
			                    System.out.println("\n" + image.attr("src"));
			                    try(InputStream in = new URL(image.attr("src")).openStream()){
			                    	//edit the FilePath where you want the picture to be stored.
			                    
			                        Files.copy(in, Paths.get(userpass+imgCount+".png"));
			                    }
			                    
			                   
			                                            
			                }
		
			         
					 
	}
					 
					 //level 4
					 
					 for (int i = 0;i<=30;i++) {
							
						 imgCount++;
						 
						 
			                Document doc = Jsoup.connect("https://prnt.sc/p"+c[i]+c[i]+c[i]+c[i]+c[i]).get();
			                //We noticed with C.Senges that most of the pictures were uploaded to Imgu.r.
			                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").not("img[src*=//st.prntscr.com/2019/09/03/1652/img/footer-logo.png]");
			                for (Element image : images) {
		                        
			                    System.out.println("\n" + image.attr("src"));
			                    try(InputStream in = new URL(image.attr("src")).openStream()){
			                    	//edit the FilePath where you want the picture to be stored.
			                    
			                        Files.copy(in, Paths.get(userpass+imgCount+".png"));
			                    }
			                    
			                   
			                                            
			                }
		
			         
					 
	}
					 //level 5
					 
					 for (int i = 0;i<=30;i++) {
							
						 imgCount++;
						 
						 
			                Document doc = Jsoup.connect("https://prnt.sc/"+c[i]+c[i]+c[i]+c[i]+c[i]+c[i]).get();
			                //We noticed with C.Senges that most of the pictures were uploaded to Imgu.r.
			                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").not("img[src*=//st.prntscr.com/2019/09/03/1652/img/footer-logo.png]");
			                for (Element image : images) {
		                        
			                    System.out.println("\n" + image.attr("src"));
			                    try(InputStream in = new URL(image.attr("src")).openStream()){
			                    	//edit the FilePath where you want the picture to be stored.
			                    
			                        Files.copy(in, Paths.get(userpass+imgCount+".png"));
			                    }
			                    
			                   
			                                            
			                }
		
			         
					 
	}
					 
					 
					 
					 
					  } catch (IOException e) {
			                e.printStackTrace();
			                                   }
					                                                  }
					   
	                      }
			
		   




