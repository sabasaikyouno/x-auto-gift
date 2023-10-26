import java.io.File

import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import scala.collection.mutable

object Replied {
  def repliedList = {
    val reader = CSVReader.open(new File("src\\main\\resources\\Replied.csv"))

    reader.all().head
  }

  def repliedWrite(list: mutable.HashSet[String]) = {
    val writer = CSVWriter.open(new File("src\\main\\resources\\Replied.csv"))

    writer.writeRow(list.toList)
  }
}
