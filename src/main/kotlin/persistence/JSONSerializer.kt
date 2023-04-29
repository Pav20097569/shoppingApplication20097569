package persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
import models.Item
import models.ShoppingList
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class JSONSerializer(private val file: File) : Serializer {

    /**
     * Read an object from a JSON file.
     *
     * @throws Exception if there is an error reading the object.
     * @return the object read from the file.
     */
    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(JettisonMappedXmlDriver())
        xStream.allowTypes(arrayOf(ShoppingList::class.java))
        xStream.allowTypes(arrayOf(Item::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    /**
     * Write an object to a JSON file.
     *
     * @throws Exception if there is an error writing the object.
     * @param obj the object to be written.
     */
    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(JettisonMappedXmlDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}
