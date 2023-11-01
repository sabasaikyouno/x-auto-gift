import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

import scala.jdk.CollectionConverters._

object TwitterFollowing {

  def followingList(id: String)(implicit chrome: ChromeDriver) = {
    //フォロー一覧サイトにアクセス
    chrome.get(s"https://twitter.com/$id/following")

    //フォロー一覧の要素取得
    val followingEle = chrome
      .findElement(By.xpath("//div[@aria-label='タイムライン: フォロー中']"))
      .findElements(By.xpath("//a[@tabindex='-1' and @class='css-4rbku5 css-18t94o4 css-1dbjc4n r-1loqt21 r-1wbh5a2 r-dnmrzs r-1ny4l3l']"))

    //フォロー一覧のプロフィールurlを抽出
    followingEle
      .asScala
      .map(
        _.getAttribute("href")
      ).toList
  }
}
