package lms;

import java.io.File;

public interface BlobConverter {
    void convert_file_to_blob(File file, String username);
}

