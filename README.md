## HTTP response
**HTTPCodeAnalysis** was created to track http request and save it for future analysis, the user have to choose which stream he want to save it.

[![](https://jitpack.io/v/LeonGrn/HttpCodeAnalysis.svg)](https://jitpack.io/#LeonGrn/HttpCodeAnalysis)

#### Download
#### Requirement
```
minSdkVersion 23
```
#### Repository

Add this in your root  `build.gradle`  file (**not**  your module  `build.gradle`  file):
```
allprojects {
 repositories {
  ...
  maven { url "https://jitpack.io" }
 }
}

```
#### Dependency
Add this to your module's  `build.gradle`  file (Note: version should match the jitpack badge above)
```
dependencies {
 implementation 'com.github.LeonGrn:HttpCodeAnalysis:1.0.0'
}
```
## Usage (for this example i will choose to save the requests to file)
### First lets initialize the objects
```
    HttpCode myHttp;
    PrintWriter pw;
    File file;
```
### Now lets create an instance for each one
```
    String filePath = this.getFilesDir().getPath().toString() + "/HTTPCODE1.txt";
    file = new File(filePath);

    try {
        pw = new PrintWriter(new FileOutputStream(
                file,
                true /* append = true */));
        myHttp = new HttpCode(pw);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
```
### In my example to create the HTTP request i use volley SDK(this function was override from volley library)
```
@Override
     protected Response<String> parseNetworkResponse(NetworkResponse response) {
         String mStatusCode = String.valueOf(response.statusCode);
         myHttp.CheckHttpCode(mStatusCode);
         try {
             BufferedReader br = new BufferedReader(new FileReader(file));
             String st;
             while ((st = br.readLine()) != null)
                 System.out.println(st);
         }
         catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         catch (IOException e) {
             e.printStackTrace();
         }
         return super.parseNetworkResponse(response);
     }
```
