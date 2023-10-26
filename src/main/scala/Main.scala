import TwitterFollowing.followingList
import TwitterGetTweet.tweetList
import TwitterLogin.login
import org.openqa.selenium.chrome.ChromeDriver

object Main {

  def main(args: Array[String]) = {
    implicit val chrome: ChromeDriver = new ChromeDriver()

    val twitterId = "@forsuger169566"
    login(twitterId, "liens1234")

    val f = followingList(twitterId)
      .flatMap(following => tweetList(following))

    println(f)
  }
}
