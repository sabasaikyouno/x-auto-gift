import java.io.File

import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import scala.collection.mutable

object LoadedTweets {
  def loadedList = {
    val reader = CSVReader.open(new File("src\\main\\resources\\loaded.csv"))

    reader.all().head
  }

  def loadedWrite(list: mutable.HashSet[String]) = {
    val writer = CSVWriter.open(new File("src\\main\\resources\\loaded.csv"))

    writer.writeRow(list.toList)
  }
}
