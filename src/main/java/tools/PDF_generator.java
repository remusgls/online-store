package tools;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.line.LineStyle;
import model.cart.CartItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import types.InvoiceTypes;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDF_generator {
    public void generatePDFtable(List<CartItem> cartItems) {
        InvoiceTypes invoiceTypes = new InvoiceTypes();

        PDPage myPage = new PDPage(PDRectangle.A4);
        PDDocument mainDocument = new PDDocument();
        //Dummy Table
        float margin = 50;
// starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = myPage.getMediaBox().getHeight() - margin;
// we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float bottomMargin = 70;
// y position is your coordinate of top left corner of the table
        float yPosition = 0;

        BaseTable table = null;
        try {
            table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, drawContent);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //table header
        Row<PDPage> headerRow = table.createRow(10f);
        Cell<PDPage> cell = headerRow.createCell(100, invoiceTypes.STORE_SIGN);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setFontSize(6);
        cell.setTextColor(Color.GRAY);
        cell.setBottomPadding(10);
        cell.setBorderStyle(null);
        table.addHeaderRow(headerRow);

        String someString = "Online Store Sesame Street";
        String someString2 = "Online Store  Sesame Street";

        Row<PDPage> row = table.createRow(12);
        cell = row.createCell(50, invoiceTypes.STORE_NAME + "<br>" + invoiceTypes.STORE_ADDRESS + "<br>" + invoiceTypes.STORE_CITY + "<br>" + invoiceTypes.STORE_PHONE + invoiceTypes.STORE_CAPITAL_SOCIAL);
        cell.setBorderStyle(null);

        cell = row.createCell(50, someString2 + "<br>" + someString);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBorderStyle(null);

        //table separator
        Row<PDPage> separatorRow = table.createRow(40);
        cell = separatorRow.createCell(100, null);
        cell.setBorderStyle(null);

        //table entries title
        Row<PDPage> headerInvoiceTitleRow = table.createRow(20);
        cell = headerInvoiceTitleRow.createCell(100, "FACTURA");

        cell.setFontSize(20);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setBorderStyle(null);
        cell.setBottomBorderStyle(new LineStyle(Color.BLACK, 0.5f));
        cell.setBottomPadding(30);

        // table entries -- header
        Row<PDPage> headerTableEntries = table.createRow(20);
        cell = headerTableEntries.createCell(5, "NR.");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(28, "DENUMIRE PRODUS");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(10, "BUC");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(12, "DISCOUNT");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(15, "PRET U");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(15, "PRET");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        cell = headerTableEntries.createCell(15, "PRET DUPA DISCOUNT");
        cell.setFont(PDType1Font.HELVETICA_BOLD);

        int pretTotalFaraDiscount = 0;
        int pretTotalCuDiscount = 0;
        int i = 1;
        // table entries -- entries
        for (CartItem cartItem: cartItems) {
            Row<PDPage> contentTableEntries = table.createRow(5);
            cell = contentTableEntries.createCell(5, Integer.toString(i + 1));
            cell.setRightBorderStyle(null);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(28, cartItem.getProduct());
            cell.setRightBorderStyle(null);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(10, Integer.toString(cartItem.getBuc()));
            cell.setRightBorderStyle(null);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(12, Integer.toString(cartItem.getDiscount()));
            cell.setRightBorderStyle(null);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(15, Integer.toString(cartItem.getPretUnitar()) + invoiceTypes.CURRENCY_TYPE);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(15, Integer.toString(cartItem.getPretFaraDiscount()) + invoiceTypes.CURRENCY_TYPE);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            cell = contentTableEntries.createCell(15, Integer.toString(cartItem.getPretCuDiscount()) + invoiceTypes.CURRENCY_TYPE);
            if (i != cartItems.size() - 1) {
                cell.setBottomBorderStyle(null);
            }

            pretTotalCuDiscount += cartItem.getPretCuDiscount();
            pretTotalFaraDiscount += cartItem.getPretFaraDiscount();

            i++;
        }

        // table entries -- footer total
        Row<PDPage> footerTableEntries = table.createRow(20);
        cell = footerTableEntries.createCell(70, "TOTAL");
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setFillColor(Color.cyan);

        cell = footerTableEntries.createCell(15, Integer.toString(pretTotalFaraDiscount) + invoiceTypes.CURRENCY_TYPE);
        cell = footerTableEntries.createCell(15, Integer.toString(pretTotalCuDiscount) + invoiceTypes.CURRENCY_TYPE);

        //table entries -- footer semnatura
        Row<PDPage> userDataTableEntries = table.createRow(50);
        cell = userDataTableEntries.createCell(100, invoiceTypes.SEMNATURA + " / " + invoiceTypes.DATA);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setLineSpacing(20);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setBorderStyle(null);
        cell.setTopPadding(50);
        cell.setFontSize(12);

        try {
            table.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainDocument.addPage(myPage);

        try {
            mainDocument.save("invoices/somthingnew.pdf");
            mainDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
