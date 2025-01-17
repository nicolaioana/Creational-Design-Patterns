public class Main {

    public interface Document {
        void save(String content);
        void display();
    }

    public static class PDFDocument implements Document {
        private String content;

        @Override
        public void save(String content) {
            this.content = content;
            System.out.println("Saving content as PDF: " + content);
        }

        @Override
        public void display() {
            System.out.println("Displaying PDF content: " + content);
        }
    }

    public static class WordDocument implements Document {
        private String content;

        @Override
        public void save(String content) {
            this.content = content;
            System.out.println("Saving content as Word Document: " + content);
        }

        @Override
        public void display() {
            System.out.println("Displaying Word Document content: " + content);
        }
    }

    public static class HTMLDocument implements Document {
        private String content;

        @Override
        public void save(String content) {
            this.content = content;
            System.out.println("Saving content as HTML: " + content);
        }

        @Override
        public void display() {
            System.out.println("Displaying HTML content: " + content);
        }
    }

    public static class DocumentFactory {
        public static Document createDocument(String type) {
            return switch (type.toLowerCase()) {
                case "pdf" -> new PDFDocument();
                case "word" -> new WordDocument();
                case "html" -> new HTMLDocument();
                default -> throw new IllegalArgumentException("Unsupported document type: " + type);
            };
        }
    }

    public static class DocumentManager {
        private static DocumentManager instance;
        private Document currentDocument;

        private DocumentManager() {}

        public static DocumentManager getInstance() {
            if (instance != null) {
                return instance;
            }
            synchronized (DocumentManager.class) {
                if (instance == null) {
                    instance = new DocumentManager();
                }
            }
            return instance;
        }

        public void createNewDocument(String type) {
            currentDocument = DocumentFactory.createDocument(type);
            System.out.println("Created new document of type: " + type);
        }

        public void saveDocument(String content) {
            if (currentDocument == null) {
                System.out.println("No document created yet.");
            } else {
                currentDocument.save(content);
            }
        }

        public void displayDocument() {
            if (currentDocument == null) {
                System.out.println("No document created yet.");
            } else {
                currentDocument.display();
            }
        }
    }

    public static void main(String[] args) {
        DocumentManager manager = DocumentManager.getInstance();

        manager.createNewDocument("pdf");
        manager.saveDocument("PDF Example Content");
        manager.displayDocument();

        manager.createNewDocument("word");
        manager.saveDocument("Word Example Content");
        manager.displayDocument();

        manager.createNewDocument("html");
        manager.saveDocument("HTML Example Content");
        manager.displayDocument();
    }
}
