package io.umarket.worker;

import java.time.LocalDateTime;

public class WorkerApplication {

    public static void main(String[] args) {
        System.out.println("--- UMARKET WORKER: INICIADO EXITOSAMENTE ---");
        System.out.println("--- Ejecutando tareas pesadas en segundo plano... ---");
        
        // Bucle infinito para mantener el Worker corriendo dentro del contenedor
        while (true) {
            try {
                
                System.out.println(">>> WORKER MESSAGE: Tarea en segundo plano ejecutada a las: " + LocalDateTime.now());
                
                // Simular el trabajo (espera 5 segundos)
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Worker detenido.");
                break;
            }
        }
    }
}