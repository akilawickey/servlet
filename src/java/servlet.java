import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;



public class servlet extends HttpServlet {
	static int hitCount = 0;

	String allCards[] = {"cards/0_1.png","cards/0_2.png","cards/0_3.png","cards/0_4.png","cards/0_5.png","cards/0_6.png","cards/0_7.png","cards/0_8.png","cards/0_9.png","cards/0_10.png","cards/0_11.png","cards/0_12.png","cards/0_13.png","cards/1_1.png","cards/1_2.png","cards/1_3.png","cards/1_4.png","cards/1_5.png","cards/1_6.png","cards/1_7.png","cards/1_8.png","cards/1_9.png","cards/1_10.png","cards/1_11.png","cards/1_12.png","cards/1_13.png","cards/2_1.png","cards/2_2.png","cards/2_3.png","cards/2_4.png","cards/2_5.png","cards/2_6.png","cards/2_7.png","cards/2_8.png","cards/2_9.png","cards/2_10.png","cards/2_11.png","cards/2_12.png","cards/2_13.png","cards/3_1.png","cards/3_2.png","cards/3_3.png","cards/3_4.png","cards/3_5.png","cards/3_6.png","cards/3_7.png","cards/3_8.png","cards/3_9.png","cards/3_10.png","cards/3_11.png","cards/3_12.png","cards/3_13.png"};
	String[][] cards = new String[5][15];
	int k,i,j,n;
	
        
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
                StringBuilder strBuild = new StringBuilder();
                response.setContentType("text/html"); 
                PrintWriter out = response.getWriter();
                String name = request.getParameter("start");
                
                
                //----------Count the Players-------------
                if(name.equals("start")){
                    hitCount++;
                if(hitCount == 5)   hitCount = 1;
                if(hitCount < 5 ) out.print(Integer.toString(hitCount)+"*");
                }
               //----------Devide the cards for 4 players--------------
                    try{
                        Random randomGenerator = new Random();
                            if(hitCount < 5){

                                strBuild.append("\"{\\\"cards\\\":[");
                                    for (i = 0; i < 13; i++){
                                        n = i;
                                        k = randomGenerator.nextInt(52);

                                        while(allCards[k] == null){
                                                k = randomGenerator.nextInt(52);
                                        }
                                      
                                        cards[hitCount][i] = allCards[k];
                                        allCards[k] = null;
                                        strBuild.append("{\\\"image\\\":\\\"");
                                        strBuild.append(cards[hitCount][i]);
                                        strBuild.append("\\\"}");
                                        if(n<12)
                                            strBuild.append(",");
                                        else	
                                            //strBuild.append("],\\\"card1\\\":\\\"cards/3_4.png\\\" , \\\"card2\\\":\\\"cards/3_1.png\\\",\\\"showHand\\\" : true, \\\"showCards\\\" : true , \\\"message\\\" : \\\"Play your card\\\"}\"");	
                                              strBuild.append("],\\\"showHand\\\" : true, \\\"showCards\\\" : true , \\\"message\\\" : \\\"Play your card\\\"}\"");	
                                      
                                        }
                            }
                            }
                            catch(ArrayIndexOutOfBoundsException e1){}
                    
                    String string = strBuild.toString();
                    out.print(string);
               
   
        }
    
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
            
            StringBuilder strBuild2 = new StringBuilder();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            String card = request.getParameter("card");//Get the card player played
            String id = request.getParameter("id");    //Get the Player id
            int p = Integer.parseInt(id);
            
            //--------Remove the plyed card from card list-----
            strBuild2.append("\"{\\\"cards\\\":[");
            for(int i = 0;i<13;i++){
                if(cards[p][i].equals(card)){
                    int k = i;
                    
                    for(int j = k;j<12;j++)
                    cards[p][j] = cards[p][j+1];
                }
                
            }
           //-------send the json object string--------------
            for(int i = 0; i<12;i++){
              strBuild2.append("{\\\"image\\\":\\\""); 
              strBuild2.append(cards[p][i]);
              strBuild2.append("\\\"}");
              if(i <11)
               strBuild2.append(",");
              else
               strBuild2.append("],\\\"card1\\\":\\\"cards/3_4.png\\\" , \\\"card2\\\":\\\"cards/3_1.png\\\",\\\"showHand\\\" : true, \\\"showCards\\\" : true , \\\"message\\\" : \\\"Play your card\\\"}\"");
                  }
            String string = strBuild2.toString();
             out.print(string);
            
           
	}

}



