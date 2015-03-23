#### 2. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.

 * 요청이 들어오면 web.xml에서 welcom-file로 index.jsp로 받는다.
 
 * index.jsp는 next.list주소를 넘긴다.
 
 * dispatcherServlet 객체의 init()이 어디에서 실행되는지 정확히 모르겠으나, init을 하면 RequestMapping 객체를 생성하고, initMapping(을 실행하여 주소를 각 컨트롤러와 연결한다.
 
 * 각 컨트롤러의 service()메소드를 실행하면 각각의 뷰를 실행함. 
 
 * Response객체를 사용하여 클라이언트에게 응답을 보낸다. 
  
 * service()메소드가 끝나면 스레드 소멸하거나 스레드 풀로 돌려보낸다. 
   
 * 클라이언트는 응답을 받는다.
     

#### 3. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* web.xml에 설정되어 있는 welcome-file에 의해서 index.jsp를 읽어들인다

* index.jsp에는 /next.list 주소가 redirect로 주어져있다. 

* dispatcherServlet이 이걸 받는건가?

* 그리고 request에 딸려온 주소는 requestMapping 객체의 findController에 의해서 해당하는 ListController를 가지고 execute한다.

* 여기에서 list.jsp를 이용해서 뷰를 만들어 리턴한다. 

#### 10. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.

* 필드에 변수를 생성해 놓았을 때, 각각의 스레드들이 힙에 올라가있는 하나의 인스턴스를 사용하기 때문에 참조하는데 오류가 발생한다. 필드 변수를 모두 메소드 안으로 옮겼다.
답을 만들어 놓았다.. 

