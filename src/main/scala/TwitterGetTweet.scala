import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.joda.time.{DateTime, Days}
import org.joda.time.LocalDate

import scala.jdk.CollectionConverters._
import scala.util.Try

object TwitterGetTweet {
  def tweetList(url: String)(implicit chrome: ChromeDriver) = {
    //ツイート検索
    chrome.get(makeSearchUrl(url))
    Thread.sleep(3000)

    Try {
      //ツイートを取得する
      chrome
        .findElement(By.xpath("//div[@aria-label='タイムライン: タイムラインを検索']"))
        .findElements(By.cssSelector("css-4rbku5.css-18t94o4.css-901oao.r-14j79pv.r-1loqt21.r-xoduu5.r-1q142lx.r-1w6e6rj.r-1tl8opc.r-a023e6.r-16dba41.r-9aw3ui.r-rjixqe.r-bcqeeo.r-3s2u2q.r-qvutc0"))
        .asScala
        .map(_.getAttribute("href"))
        .toList
    }.getOrElse(List())
  }

  //一週間以内、リツイート300以上の検索urlを作る
  def makeSearchUrl(url: String) = {
    val userId = url.drop(20)
    val since = new LocalDate().minusDays(7).toString

    s"https://twitter.com/search?q=from%3A$userId%20min_retweets%3A300%20since%3A$since&src=typed_query&f=live"
  }

}
