package interfaces

// interface delegation lets us add features to the class via composition
// composition is when we use instance of another class as opposed to inherit from it

interface SmartPhoneActions {

    // abstract methods
    fun call(number: Int)
    fun clickPhotos()
    fun playMusic()
    fun installApp(appName: String)
}


interface OsVersion {
    val osVersion: String
}

interface OsName {
    val os: String
}

interface PhoneName {
    val phoneName: String
}


// implementing interfaces
// object let us create only single instance of class, not more then one instance can be created
// Some operating  Systems
object AndroidOs: OsName{ override val os: String = "Android" }
object IOS : OsName { override val os: String = "IOS" }

// Some phone names
object GooglePixel: PhoneName { override val phoneName = "Google Pixel" }
object Iphone11: PhoneName { override val phoneName = " IPhone 11 "}

// Some android Os versions
object AndroidPie: OsVersion { override val osVersion: String = " Android pie"}
object AndroidOreo: OsVersion { override val osVersion: String = "Android Oreo"}

// Some IOS versions
object Ios11: OsVersion { override val osVersion: String = "IOS 11"}
object Ios13: OsVersion { override val osVersion: String =  "IOS 13"}

// we don't need to create single instance  or object for every Phone name and version
// we can use class instead to do soo as bellow

// Phone name can be set on object creation
class Phone(name: String): PhoneName {override val phoneName = name }

// Version can be set on object creation
class Version(version: String): OsVersion { override val osVersion  = version }

 // implemented interface SmartPhoneActions
 class  BasicActions: SmartPhoneActions {
    override fun call(number: Int) { println("Calling $number")}
    override fun clickPhotos() { println("Photo clicked")}
    override fun playMusic() { println("playing Music...")}
    override fun installApp(appName: String) {println("Installing $appName")}
}


// Interface delegation...

// lets create android smart Phone  which has android x


class Android(phoneName: PhoneName, version: OsVersion):
        PhoneName by phoneName,
        OsName by AndroidOs,
        OsVersion by version,
        SmartPhoneActions by BasicActions(){

    fun aboutPhone():String  {

        return """
            Phone Name: $phoneName 
            OS Name: $os
            OS Version: $osVersion
        """.trimIndent()
    }
}

// One more thing in interface delegation
interface TestA {
    val mName: String
    fun printName()

}

// concrete class that implements TestA Interface
class ImplTestA(name: String): TestA{
    override val mName: String = name
    override fun printName() {
        println("Hello, I'm $mName")
    }
}

// interface delegation

class DelegationTest: TestA by ImplTestA("Java"){

    // lets change or override the mName property
    override val mName: String = "kotlin"

}

// now lets try to run the printName() method and see what happens

fun printNameTest() {
    val obj: DelegationTest = DelegationTest()

    // lets print mName which we have overridden
    println(obj.mName)

    // Now lets call the function or method printName() which we have'nt modified
    obj.printName() // this method will still println Hello, I'm java
    //but why

    // because the member that are overridden (mName) in this way don not get called by the members(printName) of delegated objects
    // which can only access its own implementation of interface members.



}



fun main(){

    // we can pass GooglePixel as PhoneName because it is implementing PhoneName interface
    // also we can pass Version class object because it is implementing  OsVersion interface
    val myAndroid: Android  = Android(GooglePixel, AndroidPie)

    // and we can also create and pass object of Phone because it is implementing PhoneName Interface
    // also can pass object of Version because it is implementing Os version
    val anotherAndroid: Android = Android(Phone("Motorola"), Version("Android One") )

    println(myAndroid.aboutPhone())
    println("-----------------------------")
    println(anotherAndroid.aboutPhone())

    // Delegation test (last example before main function)
    printNameTest()


}

fun printOs(phone: OsVersion) {
    println(phone.osVersion)
}