package pdfTest;

import by.clevertec.Entity.Entity;
import by.clevertec.PDF.PDFPrint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PDFPrintTest {
    private PDFPrint pdfPrint;
    private Entity entity;

    @BeforeEach
    public void setUp() {
        pdfPrint = new PDFPrint();
        entity = Mockito.mock(Entity.class);
    }

    @Test
    public void testPrint() {
        when(entity.getName()).thenReturn("TestName");
        when(entity.getSurname()).thenReturn("TestSurname");

        pdfPrint.Print(entity);

        verify(entity, times(1)).getName();
        verify(entity, times(1)).getSurname();
    }
}
