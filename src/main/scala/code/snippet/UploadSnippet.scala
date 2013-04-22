package code
package snippet

import scala.collection.mutable.ListBuffer

import net.liftweb.http.{S, SHtml, JsonHandler, FileParamHolder, SessionVar}
import net.liftweb.http.S._
import net.liftweb.http.SHtml._
import net.liftweb.http.js._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JE._
import net.liftweb.common._
import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.json._
import net.liftweb.json.JsonDSL._

class FileUploadSnippet {
  object files extends SessionVar[ListBuffer[FileParamHolder]](ListBuffer.empty)

  def render = {
    val fileFunc: FileParamHolder => Any = fp => if (fp.length > 0) uploadFile(fp)

    "#uploadScript" #> Script {
      fmapFunc(BinFuncHolder(fileFunc)) { name =>
        JsRaw(s"uploadUrl = '/upload/$name'")
      }
    }
  }

  def uploadFile(file: FileParamHolder): JValue = {
    files.is.append(file)
    val response = (
      ("name" -> file.fileName)
      ~ ("type" -> file.mimeType)
      ~ ("size" -> file.length)
    )
    response
  }
}
