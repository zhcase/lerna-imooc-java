<%
  for( int i=2;i<=1000;i++){
    boolean flag=true;
    for(int j=2;j<i;j++){
        if(i%j==0){
            flag=false;
            break;
        }
    }
    if(flag==true){
      out.println("<h1>"+i+"</h1>");
    }
  }
%>