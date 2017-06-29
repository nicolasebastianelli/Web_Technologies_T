
//lettura file da servlet
filePath = "/files/"+fileName;
URL fileUrl = getServletContext().getResource(filePath);
if(fileUrl==null){
    req.setAttribute("stato", "File "+fileName+" inesistente");
    req.getRequestDispatcher(homeURL).forward(req, resp);
    return;
}
    File f = new File(fileUrl.getFile());
    long fileSize =f.length();
    BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(filePath)));
    br.skip(i*1024); //saltare i primi n caratteri/byte
    long bytesToRead = 1024;
    while (bytesToRead > 0 && (read = br.read()) >=0) {
            bytesToRead--;
            if (read == c.getCarattere())
                count++;
            }
    }
    br.close();
}


//scrittura file da servlet

filePath = "/files/"+fileName;
URL fileUrl = getServletContext().getResource(filePath);
if(fileUrl==null){
    req.setAttribute("stato", "File inesistente, creo file: "+fileName);
    File f = new File(getServletContext().getRealPath("/files/")+fileName);
    FileOutputStream fout = new FileOutputStream(f);
    PrintStream printer = new PrintStream(fout);
    printer.println("text");
    fout.close();
}

// download tramite Url

StringBuilder sb = new StringBuilder();
InputStream is = null;
try {
    URL url = new URL(url1);
    is = url.openStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line;
    while ((line = br.readLine()) != null) {
    sb.append(line);
    }
} catch (MalformedURLException mue) {
    mue.printStackTrace();
} catch (IOException ioe) {
    ioe.printStackTrace();
} finally {
    try {
        if (is != null) is.close();
        } catch (IOException ioe) {
// nothing to see here
    }
}

//lettura file normale
File file = new File("myfile.txt");
if (!file.exists()) {
    file.createNewFile();
}
FileOutputStream fout = new FileOutputStream(file);
PrintStream printer = new PrintStream(fout);
printer.println("text");
fout.flush();
fout.close();

//scrittura file normale
File f = new File("file.txt");
if(!f.exists())
{
    f.createNewFile();
}
FileOutputStream fos = new FileOutputStream(f);
PrintWriter out = new PrintWriter(fos);
for(Element s : enements)
{
    if()
    {
        out.println();
    }
}
out.close();
System.out.println("Written to file!");


//lettura da file binario
String sContent=null;
byte [] buffer =null;
File a_file = new File(filename);
try
{
    FileInputStream fis = new FileInputStream(filename);
    int length = (int)a_file.length();
    buffer = new byte [length];
    fis.read(buffer);
    fis.close();
}
catch(IOException e)
{
    e.printStackTrace();
}
sContent = new String(buffer);


//scrittura su file binario

OutputStream out = null;
String testString = "This String is for testing";
try{
    out = new FileOutputStream(fileTo);
    byte[] totalBytes = testString.getBytes();
    out.write(totalBytes);
}
finally{
    if(out !=null)
        out.close();
}
