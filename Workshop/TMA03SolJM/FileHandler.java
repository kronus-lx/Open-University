import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;  
import java.io.Closeable;

public abstract class FileHandler implements Closeable
{
    private String filePath;
    
    private String fileName;

    public FileHandler(String path, String fname)
    {
        this.filePath = path;
        this.fileName = fname;
    }

    public static FileHandler csvFileHandler(String path, String fname) throws IOException, Exception
    {
        FileHandler csv = new CSVHandler(path, fname);

        return csv;
    }

    public abstract boolean write(String value);

    public abstract String read();

    public String filePath() 
    {
        return new File(this.filePath, this.fileName).getAbsolutePath();
    }

    public String filename()
    {
        return this.fileName;
    }

    @Override
    public void close() throws IOException, SecurityException, OutOfMemoryError {}
}

class CSVHandler extends FileHandler
{
    private File handler;
    
    public CSVHandler(String path, String fname)
    {
        super(path, fname);

        this.handler = new File(path, fname);
        
        if(this.handler == null){
            throw new IllegalArgumentException("[ERROR]: Invalid Path Assigned to FileHandler");
        }
    }

    @Override
    public boolean write(String value)
    {   
        try
        {
            if(!this.handler.exists())
            {
                try
                {
                    this.handler.createNewFile();
                }
                catch(IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }

            try(FileWriter writer = new FileWriter(handler, true))
            {
                writer.append(String.valueOf(value))
                .append(',')
                .append('\n');
            }

            return true;
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
            
            ex.printStackTrace();

            return false;
        }
        catch(SecurityException ex)
        {
            System.err.println(ex.getMessage());
            
            ex.printStackTrace();
            
            return false;
        }
    }   
    @Override
    public String read()
    {
        try
        {
            if(!this.handler.exists())
            {
                try
                {
                    handler.createNewFile();
                }
                catch(IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            } 
            return Files.readString(Path.of(handler.getAbsolutePath()));
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            
            ex.printStackTrace();
            
            return null;
        }
        catch(SecurityException ex)
        {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
            
            return null;
        }
        catch(OutOfMemoryError ex)
        {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
            
            return null;
        }
    }
}