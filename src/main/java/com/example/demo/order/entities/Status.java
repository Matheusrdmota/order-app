package com.example.demo.order.entities;

public enum Status {
    //STATE
    PENDENTE{
        public void changeToPending(Order order){
            throw new IllegalStateException("Transição de PENDENTE para PENDENTE é inválida!");
        }

        public void changeToAwaitingForPayment(Order order){
            order.setStatus(Status.AGUARDANDO_PAGAMENTO);
        }

        public void changeToFinished(Order order){
            throw new IllegalStateException("Transição de PENDENTE para CONCLUIDO é inválida!");
        }

        public void changeToCanceled(Order order){
            order.setStatus(Status.CANCELADO);
        }
    },
    AGUARDANDO_PAGAMENTO{
        public void changeToPending(Order order){
            throw new IllegalStateException("Transição de AGUARDANDO_PAGAMENTO para PENDENTE é inválida!");
        }

        public void changeToAwaitingForPayment(Order order){
            throw new IllegalStateException("Transição de AGUARDANDO_PAGAMENTO para AGUARDANDO_PAGAMENTO é inválida!");
        }

        public void changeToFinished(Order order){
            order.setStatus(Status.CONCLUIDO);
        }

        public void changeToCanceled(Order order){
            order.setStatus(Status.CANCELADO);
        }
    },
    CONCLUIDO{
        public void changeToPending(Order order){
            throw new IllegalStateException("Transição de CONCLUIDO para PENDENTE é inválida!");
        }

        public void changeToAwaitingForPayment(Order order){
            throw new IllegalStateException("Transição de CONCLUIDO para AGUARDANDO_PAGAMENTO é inválida!");
        }

        public void changeToFinished(Order order){
            throw new IllegalStateException("Transição de CONCLUIDO para CONCLUIDO é inválida!");
        }

        public void changeToCanceled(Order order){
            throw new IllegalStateException("Transição de CONCLUIDO para CANCELADO é inválida!");
        }
    },
    CANCELADO{
        public void changeToPending(Order order){
            order.setStatus(Status.PENDENTE);
        }

        public void changeToAwaitingForPayment(Order order){
            throw new IllegalStateException("Transição de CANCELADO para AGUARDANDO_PAGAMENTO é inválida!");
        }

        public void changeToFinished(Order order){
            throw new IllegalStateException("Transição de CANCELADO para CONCLUIDO é inválida!");
        }

        public void changeToCanceled(Order order){
            throw new IllegalStateException("Transição de CANCELADO para CANCELADO é inválida!");
        }
    };

    public abstract void changeToPending(Order order);
    public abstract void changeToAwaitingForPayment(Order order);
    public abstract void changeToFinished(Order order);
    public abstract void changeToCanceled(Order order);
}
