package data_class

// we have as a programmers have noticed some class Purpose is to just hold some data
// Kotlin provides special class for just for holding data.And They are called data classes

// properties with val have only getter (get()) and not setter (set()) methods
// properties with var have both getter and setter { get() and set() }
data class Person(val name: String, val age: Int)

fun main() {
    // creating instances of Person Data Class
    val david = Person("David", 31)

    val  alex = Person("Alex", 21)
    println(alex) // has by default toString() method

    val john = Person("John", 19)
    val alexClone = Person("Alex", 21)
    val alexFakeClone = Person("Alex", 31)


    // implicitly generated equals() method


    // returns true if both properties are sane
    println(alexClone.equals(alex))  // Returns true because both properties are same
    println(alexFakeClone.equals(alex)) // Returns false because age properties are not equal

    // also have copy() method to generate new object with same or different values
    val johnTwo = john.copy() // new instance of Person data class with same values or date
    val modifiedDavid = david.copy("Dr.David", 34) // new instance with different or modified properties values
    // can use when we have to copy exact same data or want some properties value  modified in new created instance

    // decomposition
    val mitch = Person("Mitch", 28)
    val (name: String, age: Int) = mitch
    println("Hi, I'm $name and I'm $age years old.")

    //Note -: Number of variables must match the Number of properties
    // Variables are assigned in the order they are declared in the data class

    // decomposition is done by using componentN() methods generated by data class
    println("${david.component1()} and ${david.component1()}")

    // so for Decomposition we need componentN() methods

}

// what if a data class holds another data class
// lets check how equality works for data class in that case
data class PersonWrapperDataClass(val person: Person)
fun dataClassEqualityCheckTest() {
    // sorry for using company names as person :-)
    val personOne = Person("Kotlin", 10)
    val personTwo = Person("Google", 100 )

    // wrapping up !
    // for true case
    val wrappedPersonOne = PersonWrapperDataClass(personOne)  // name sounds scary !
    val wrappedPersonTwo = PersonWrapperDataClass(personOne)

    // for false case
    val wrappedPersonThree = PersonWrapperDataClass(personTwo)

    // Comparing data class with another data class when they both holding a data class whose contents are same in both !
    println(wrappedPersonOne == wrappedPersonTwo) // true

    // Comparing data class with another data class when they both are holding a data class whose contents are not same in both !
    println(wrappedPersonOne == wrappedPersonThree) // false

    // under the hood here we are checking for equality of two data classes by checking equality of there content
    // since here content of our both data class is also a data class
    // under the hood it is also checking for the equality of both data classes inside the both wrapper classes
}