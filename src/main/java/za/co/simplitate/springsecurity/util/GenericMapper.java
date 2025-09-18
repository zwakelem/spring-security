package za.co.simplitate.springsecurity.util;

import za.co.simplitate.springsecurity.dto.*;
import za.co.simplitate.springsecurity.entities.*;

public class GenericMapper {

    private GenericMapper() {
    }

    public static CustomerTO toCustomerTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerTO(customer.getId(), customer.getName(), customer.getEmail(),
                customer.getMobileNumber(), customer.getPwd(), customer.getRole(),
                toUtilDate(customer.getCreateDt()));
    }

    public static Customer toCustomer(CustomerTO customerTO) {
        if(customerTO == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerTO.id());
        customer.setName(customerTO.name());
        customer.setEmail(customerTO.email());
        customer.setMobileNumber(customerTO.mobileNumber());
        customer.setRole(customerTO.role());
        customer.setCreateDt(toSqlDate(customerTO.createDt()));
        return customer;
    }

    public static AccountTO toAccountTO(Accounts account) {
        if (account == null) {
            return null;
        }
        return new AccountTO(
                account.getCustomerId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAddress(),
                toUtilDate(account.getCreateDt())
        );
    }

    public static Accounts toAccounts(AccountTO accountTO) {
        if (accountTO == null) {
            return null;
        }

        Accounts account = new Accounts();
        account.setCustomerId(accountTO.customerId());
        account.setAccountNumber(accountTO.accountNumber());
        account.setAccountType(accountTO.accountType());
        account.setBranchAddress(accountTO.branchAddress());
        account.setCreateDt(toSqlDate(accountTO.createDt()));
        return account;
    }

    public static AccountTransactionsTO toAccountTransactionsTO(AccountTransactions accountTransactions) {
        if (accountTransactions == null) {
            return null;
        }

        return new AccountTransactionsTO(
                accountTransactions.getTransactionId(),
                accountTransactions.getAccountNumber(),
                accountTransactions.getCustomerId(),
                toUtilDate(accountTransactions.getTransactionDt()),
                accountTransactions.getTransactionSummary(),
                accountTransactions.getTransactionType(),
                accountTransactions.getTransactionAmt(),
                accountTransactions.getClosingBalance(),
                accountTransactions.getCreateDt()
        );
    }

    public static AccountTransactions toAccountTransactions(AccountTransactionsTO accountTransactionsTO) {
        if(accountTransactionsTO == null) {
            return null;
        }

        AccountTransactions  accountTransactions = new AccountTransactions();
        accountTransactions.setTransactionId(accountTransactionsTO.transactionId());
        accountTransactions.setAccountNumber(accountTransactionsTO.accountNumber());
        accountTransactions.setCustomerId(accountTransactionsTO.customerId());
        accountTransactions.setTransactionDt(toSqlDate(accountTransactionsTO.transactionDt()));
        accountTransactions.setTransactionSummary(accountTransactionsTO.transactionSummary());
        accountTransactions.setTransactionType(accountTransactionsTO.transactionType());
        accountTransactions.setTransactionAmt(accountTransactionsTO.transactionAmt());
        accountTransactions.setClosingBalance(accountTransactionsTO.closingBalance());
        accountTransactions.setCreateDt(toSqlDate(accountTransactionsTO.createDt()));
        return accountTransactions;
    }


    public static CardsTO toCardsTO(Cards cards) {
        if (cards == null) {
            return null;
        }
        return new CardsTO(
                cards.getCardId(),
                cards.getCustomerId(),
                cards.getCardNumber(),
                cards.getCardType(),
                cards.getTotalLimit(),
                cards.getAmountUsed(),
                cards.getAvailableAmount(),
                toUtilDate(cards.getCreateDt())
        );
    }


    public static Cards toCards(CardsTO cardsTO) {
        if (cardsTO == null) {
            return null;
        }
        Cards cards = new Cards();
        cards.setCardId(cardsTO.cardId());
        cards.setCustomerId(cardsTO.customerId());
        cards.setCardNumber(cardsTO.cardNumber());
        cards.setCardType(cardsTO.cardType());
        cards.setTotalLimit(cardsTO.totalLimit());
        cards.setAmountUsed(cardsTO.amountUsed());
        cards.setAvailableAmount(cardsTO.availableAmount());
        cards.setCreateDt(toSqlDate(cardsTO.createDt()));
        return cards;
    }

    public static ContactTO toContactTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        return new ContactTO(
                contact.getContactId(),
                contact.getContactName(),
                contact.getContactEmail(),
                contact.getSubject(),
                contact.getMessage(),
                toUtilDate(contact.getCreateDt())
        );
    }

    public static Contact toContact(ContactTO contactTO) {
        if (contactTO == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setContactId(contactTO.contactId());
        contact.setContactName(contactTO.contactName());
        contact.setContactEmail(contactTO.contactEmail());
        contact.setSubject(contactTO.subject());
        contact.setMessage(contactTO.message());
        contact.setCreateDt(toSqlDate(contactTO.createDt()));
        return contact;
    }

    public static LoansTO toLoansTO(Loans loans) {
        if (loans == null) {
            return null;
        }
        return new LoansTO(
                loans.getLoanNumber(),
                loans.getCustomerId(),
                loans.getStartDt(),
                loans.getLoanType(),
                loans.getTotalLoan(),
                loans.getAmountPaid(),
                loans.getOutstandingAmount(),
                toUtilDate(loans.getCreateDt())
        );
    }

    public static Loans toLoans(LoansTO loansTO) {
        if (loansTO == null) {
            return null;
        }
        Loans loans = new Loans();
        loans.setLoanNumber(loansTO.loanNumber());
        loans.setCustomerId(loansTO.customerId());
        loans.setStartDt(toSqlDate(loansTO.startDt()));
        loans.setLoanType(loansTO.loanType());
        loans.setTotalLoan(loansTO.totalLoan());
        loans.setAmountPaid(loansTO.amountPaid());
        loans.setOutstandingAmount(loansTO.outstandingAmount());
        loans.setCreateDt(toSqlDate(loansTO.createDt()));
        return loans;
    }

    public static NoticeTO toNoticeTO(Notice notice) {
        if (notice == null) {
            return null;
        }
        return new NoticeTO(
                notice.getNoticeId(),
                notice.getNoticeSummary(),
                notice.getNoticeDetails(),
                toUtilDate(notice.getCreateDt()),
                toUtilDate(notice.getNoticEndDt()),
                toUtilDate(notice.getCreateDt()),
                toUtilDate(notice.getUpdateDt())
        );
    }

    public static Notice toNotice(NoticeTO noticeTO) {
        if (noticeTO == null) {
            return null;
        }
        Notice notice = new Notice();
        notice.setNoticeId(noticeTO.noticeId());
        notice.setNoticeSummary(noticeTO.noticeSummary());
        notice.setNoticeDetails(noticeTO.noticeDetails());
        notice.setNoticBegDt(toSqlDate(noticeTO.noticBegDt()));
        notice.setNoticEndDt(toSqlDate(noticeTO.noticEndDt()));
        notice.setCreateDt(toSqlDate(noticeTO.createDt()));
        notice.setUpdateDt(toSqlDate(noticeTO.updateDt()));
        return notice;
    }


    private static java.sql.Date toSqlDate(java.util.Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    private static java.util.Date toUtilDate(java.sql.Date date) {
        return date == null ? null : new java.util.Date(date.getTime());
    }
}
