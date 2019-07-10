package mvc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexView {

    private String index;
    private String path;
    private String loginForm;
    private String signUpForm;


    private static IndexView ourInstance = new IndexView();

    public static IndexView getInstance() {
        return ourInstance;
    }

    private IndexView() {
    }

    public String getIndex() {
        return index;
    }

    public void setPath(String path) {
        this.path = path;
        this.index = getPartialHtml("Index");
        this.loginForm = getPartialHtml("login-form");
        this.signUpForm = getPartialHtml("SignUp");

    }

    public String getLoginForm() {
        return loginForm;
    }

    public String getSignUpForm(){ return signUpForm; }

    /**
     * Зчитує фай за адресою шляху, що є в змінній path + filename + .html
     * @param filename
     * @return об'єкт String  в якому є текст з файлу
     */
    private String getPartialHtml(String filename){
        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return strb.toString();
    }
}