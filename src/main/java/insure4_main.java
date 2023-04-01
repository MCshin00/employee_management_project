import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class insure4_main {
    final static String WEB_DRIVER_ID = "webdriver.chrome.driver"; //웹드라이버 ID = 크롬드라이버
    final static String WEB_DRIVER_PATH = "chromedriver_win32/chromedriver.exe"; //웹드라이버 PATH = 크롬드라이버 경로, 프로젝트 폴더 내에 크롬드라이버 폴더가 존재한다고 가정
    final static String Base_URL = "https://www.4insure.or.kr/ins4/ptl/data/calc/forwardInsuFeeMockCalcRenewal.do"; //4대보험 모의계산기 웹페이지 주소
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        // 크롬드라이버 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // Create a new instance of the ChromeDriver
        // 크롬드라이버 객체 생성
        // 크롬드라이버 옵션 - 크롬 창을 표시하지 않음
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Navigate to the website
        // 웹사이트 접속
        driver.get(Base_URL);
        // 0.5초 대기
        Thread.sleep(500);

        // Find the input field and enter a value
        // 월 급여 입력 필드(ID = lab0001)
        WebElement inputField = driver.findElement(By.id("lab0001"));
        // 월 급여 값 입력, 현재 값은 테스트 값이며 추후에 수정 필요함
        inputField.sendKeys("100000");

        // Find the button to submit the form and click it
        // 계산 버튼 클릭(xpath를 통한 이미지 접근)
        WebElement calculateButton = driver.findElement(By.xpath("//ul[@class='ul_btn ml10']/li[1]/a/img"));
        calculateButton.click();
        //Thread.sleep(1000);

        // Wait for the page to load and find the element with the specific value
        // 보험료 총액 - 합계 필드(ID = lab411)를 가져옴
        WebElement specificValueElement = driver.findElement(By.id("lab411"));

        // Get the text of the specific value element and print it
        // 합계 필드의 텍스트 값을 가져오고 콤마(,)를 제거
        String specificValue = specificValueElement.getAttribute("value").replaceAll(",","");
        System.out.println("Specific value: " + specificValue);

        // Close the browser
        // 브라우저 종료
        driver.quit();
    }
}

