package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should return 1")

    request {
        url "/students/1"
        method  GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }

        body(
                id: 1L,
                name: "Mark",
                grade: 85
        )
    }
}