import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.joda.time.{DateTime, Days}

import scala.jdk.CollectionConverters._

object TwitterGetTweet {
  def tweetList(url: String)(implicit chrome: ChromeDriver) = {
    //プロフィールにアクセス
    chrome.get(url)
    Thread.sleep(5000)

    //ツイートを取得し、１週間以内なら取得する
    chrome
      .findElement(By.xpath("//div[contains(@aria-label, 'Timeline:')]"))
      .findElements(By.cssSelector(".css-4rbku5.css-18t94o4.css-901oao.r-14j79pv.r-1loqt21.r-xoduu5.r-1q142lx.r-1w6e6rj.r-37j5jr.r-a023e6.r-16dba41.r-9aw3ui.r-rjixqe.r-bcqeeo.r-3s2u2q.r-qvutc0"))
      .asScala
      .filter(ele => getDiffDays(ele.getAttribute("datetime")) <= 7)
      .map(_.getAttribute("href"))
      .toList
  }

  //現在との差を日にちで表す
  private def getDiffDays(dateStr: String) = {
    val date = new DateTime(dateStr)

    Days.daysBetween(new DateTime(), date).getDays
  }

}
