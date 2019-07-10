package mvc.view;

public class UserView {
    public String getLoginPage(){
        IndexView indexView = IndexView.getInstance();
        String index = indexView.getIndex();
        String loginForm = indexView.getLoginForm();
        String singUpForm = indexView.getSignUpForm();
        String page ;//= index.replaceAll("<!--#######-->", loginForm);
        page = index.replaceAll("<!--#######-->", singUpForm);
        return page;
    }
}
