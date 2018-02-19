package hello;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
    Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @LocalServerPort
  private int port;

  private static WebDriver driver;

  @BeforeClass
  public static void setUp() throws MalformedURLException {
    DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();

//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--headless");
//    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

    String PROXY = "localhost:8080";
    Proxy proxy = new Proxy();
    proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);

    desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

    driver = new RemoteWebDriver(new URL("http://192.168.24.3:4444/wd/hub"), desiredCapabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();

  }

  @Test
  public void simplyTest() {
    driver.get("http:localhost:" + port + "/");
    //Getting Started: Serving Web Content - title
  }


}
