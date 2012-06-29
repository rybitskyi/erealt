package biz.rageintegro.erealt.domain;

public class TranslitConverter {  

     private static String alpha = new String("\u0430\u0431\u0432\u0433\u0434\u0435\u0451\u0436\u0437\u0438\u044b\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044c\u044d\u044e\u044f");

     private static String[] _alpha = {"a","b","v","g","d","e","yo","g","z","i","y","i",  

                                "k","l","m","n","o","p","r","s","t","u",  

                                "f","h","tz","ch","sh","sh","'","e","yu","ya"};  

        


     private TranslitConverter(){  
	 }


    public static String translit(String value){  
         value = value.toLowerCase();        
         StringBuffer sb = new StringBuffer("");  
         char[] chs = value.toCharArray();  
         for(int i=0; i<chs.length;i++){  
             int k = alpha.indexOf(chs[i]);  
             if(k != -1)  
                 sb.append(_alpha[k]);  
             else{  
                 sb.append(chs[i]);  
             }  
         }  
        return sb.toString();  
     }  
 }  

