import crafttweaker.api.data.IData;

var data = [1 as byte, 2 as byte] as IData;
storeData(data);
var array = data as long[];
for i in array {
    println(i);
}