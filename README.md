# 🔗 Chain of Responsibility - Payment System

Este é um projeto de estudo simples em **Java** para demonstrar o padrão de projeto comportamental **Chain of Responsibility** (Cadeia de Responsabilidade).

O objetivo é simular um sistema de pagamentos onde uma compra tenta ser debitada de várias contas em sequência (Banco, PayPal, Bitcoin) até encontrar uma que tenha saldo suficiente.

## 🧠 O Conceito

O padrão **Chain of Responsibility** permite passar pedidos por uma corrente de handlers (manipuladores). Ao receber um pedido, cada handler decide se processa o pedido ou o passa para o próximo handler da corrente.

Neste exemplo:

1. O cliente tenta pagar com a conta principal.
2. Se a conta não tiver saldo (`!canPay`), ela delega a responsabilidade para a próxima conta vinculada (`nextAccount`).
3. O processo se repete até que o pagamento seja efetuado ou a corrente acabe.

## 🛠️ Estrutura do Código

O projeto gira em torno da classe abstrata `Account`.

### `Account.java`

A classe base que contém a lógica da cadeia:

* **`nextAccount(Account account)`**: Define quem é o próximo na fila.
* **`pay(double amount)`**: O método principal. Tenta pagar com o saldo local (`this.balance`). Se não conseguir, chama o método `pay()` do próximo objeto da lista recursivamente.
* **`canPay(double amount)`**: Verificação simples de saldo.

## 🚀 Como Usar

### 1. Classes Concretas

Para o sistema funcionar, você deve criar classes que estendem `Account` e definem o saldo inicial no construtor:

```java
public class Bank extends Account {
    public Bank(double balance) {
        this.balance = balance;
    }
}

public class PayPal extends Account {
    public PayPal(double balance) {
        this.balance = balance;
    }
}

public class Bitcoin extends Account {
    public Bitcoin(double balance) {
        this.balance = balance;
    }
}

```

### 2. Executando (Main)

No seu método `main`, você cria a cadeia e executa o pagamento:

```java
public class Main {
    public static void main(String[] args) {
        // 1. Criar as contas com saldos diferentes
        Account bank = new Bank(100);      // Saldo baixo
        Account paypal = new PayPal(500);  // Saldo médio
        Account bitcoin = new Bitcoin(1000); // Saldo alto

        // 2. Configurar a corrente (Chain)
        // Bank -> PayPal -> Bitcoin
        bank.nextAccount(paypal);
        paypal.nextAccount(bitcoin);

        // 3. Tentar realizar um pagamento de $200
        // O Banco não tem saldo, então a responsabilidade deve cair para o PayPal
        try {
            bank.pay(200);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

```

## 📋 Saída Esperada

Ao executar o código acima, o console mostrará o "caminho" que o pedido percorreu:

```text
Not enough money here: Bank
You have paid $200.0 in PayPal
You have: 300.0

```

## 🔍 Detalhes de Implementação

Uma característica importante deste padrão (e deste código específico) é que **não há laços de repetição** (`for`, `while`). A iteração ocorre através da **chamada de método no próximo objeto**.

Quando `account.pay(amount)` é chamado dentro do `else if`, o contexto de execução (`this`) muda para o próximo objeto da cadeia, garantindo que o desconto (`balance -= amount`) ocorra na instância correta.

## 📝 Requisitos

* Java JDK 8 ou superior.
* Qualquer IDE Java (IntelliJ, Eclipse, VS Code).

---

*Estudo realizado para compreensão de Design Patterns em Java.*
