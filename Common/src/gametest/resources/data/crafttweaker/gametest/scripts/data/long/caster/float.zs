import crafttweaker.api.data.IData;

var data = 1L as IData;
storeData(data);
println((data as float) == (1.0 as float));