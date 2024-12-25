package tn.esprit.eventsproject.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    // Initialisation du logger
    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("execution(* tn.esprit.eventsproject.services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        // Enregistrer l'heure de début
        long start = System.currentTimeMillis();

        // Exécuter la méthode ciblée par le pointcut
        Object obj = pjp.proceed();

        // Calculer le temps écoulé
        long elapsedTime = System.currentTimeMillis() - start;

        // Logguer le temps d'exécution de la méthode
        log.info(pjp.getSignature().getName() + " Method execution time: " + elapsedTime + " milliseconds.");

        // Retourner l'objet
        return obj;
    }
}
