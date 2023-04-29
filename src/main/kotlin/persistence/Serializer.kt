package persistence

/*
    Interface for classes that can read and write objects from and to a persistent storage.
 author:  Pawel
 */
interface Serializer {

    @Throws(Exception::class)
    fun write(obj: Any?)

    @Throws(Exception::class)
    fun read(): Any?
}
