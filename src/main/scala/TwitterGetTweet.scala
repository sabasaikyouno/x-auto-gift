import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

import scala.jdk.CollectionConverters._

object TwitterGetTweet {
  def tweetList(url: String)(implicit chrome: ChromeDriver) = {
    //プロフィールにアクセス
    chrome.get(url)
    Thread.sleep(5000)

    //ツイートurl一覧の要素取得
    val tweetEle = chrome
      .findElement(By.xpath("//div[contains(@aria-label, 'Timeline:')]"))
      .findElements(By.cssSelector(".css-4rbku5.css-18t94o4.css-901oao.r-1bwzh9t.r-1loqt21.r-xoduu5.r-1q142lx.r-1w6e6rj.r-37j5jr.r-a023e6.r-16dba41.r-9aw3ui.r-rjixqe.r-bcqeeo.r-3s2u2q.r-qvutc0"))

    //ツイートurl一覧要素からツイートurlを抽出
    val t = tweetEle
      .asScala
      .map(
        _.getAttribute("href")
      ).toList

    println(tweetEle.asScala.toList)
    println(t)

    t
  }
}
