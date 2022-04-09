package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return status 200"
    request{
        method GET()
        url("/users") {
        }
    }
    response {
        status 200
    }
}
