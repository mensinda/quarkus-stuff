# Demonstration of a JTA bug in either Quarkus or narayana

This is a minimal project to reproduce a bug with Quarkus / Narayana.

## The problem

According to the [JTA spec](https://jakarta.ee/specifications/transactions/2.0/jakarta-transactions-spec-2.0.html#suspending-and-resuming-a-transaction),
when a transaction is suspended the `end()` method with the `TMSUSPEND` flag is called on all enlisted `XAResource`s.

> The application server is responsible for ensuring that the resources in use by the application are properly delisted from the suspended transaction. A resource delist operation triggers the transaction manager to inform the resource manager to disassociate the transaction from the specified resource object (XAResource.end(TMSUSPEND)).
> 
> When the application’s transaction context is resumed, the application server ensures that the resource in use by the application is again enlisted with the transaction. Enlisting a resource as a result of resuming a transaction triggers the transaction manager to inform the resource manager to re-associate the resource object with the resumed transaction (XAResource.start(TMRESUME)). Refer to “See Resource Enlistment.” and “See Transaction Association,” for more details on resource enlistment and transaction association.

Unfortunately this is not the case and the resource is neither suspended nor resumed.
To demonstrate this, this project implements a dummy `XAResource` that just loggs its method calls and a test with some manual transaction logic

## To reproduce

Just run `mvn clean verify` and observe the output

### Expected output

```
BEGIN MANUAL SUSPEND
 - START
 - END [TMSUSPEND]
   - START
   - END [TMSUCCESS]
   - COMMIT
 - START [TMRESUME]
 - END [TMSUCCESS]
 - COMMIT
END MANUAL SUSPEND
```

### Actual output

```
BEGIN MANUAL SUSPEND
 - START
   - START
   - END [TMSUCCESS]
   - COMMIT
 - END [TMSUCCESS]
 - COMMIT
END MANUAL SUSPEND
```
