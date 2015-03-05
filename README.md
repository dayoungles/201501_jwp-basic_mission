#### 2. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.

* 컨테이너가 만들어지면 HttpServletResponse 객체와 HttpServletRequest 객체를 각각 생성한다.

* 어떤 서블릿을 요청했는지 파악하여 해당 서블릿 스레드를 생성하여 request, response 객체 참조를 넘긴다. 

* 컨테이서나 servlet service() 메소드를 호출, doGet(), doPost()를 구분하여 호출한다. 
 
* Response객체를 사용하여 클라이언트에게 응답을 보낸다. 
  
* service()메소드가 끝나면 스레드 소멸하거나 스레드 풀로 돌려보낸다. 
   
* 클라이언트는 응답을 받는다.
     

#### 3. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 10. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 

