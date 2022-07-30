package com.github.trks1970

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.dataset.DataSetFormat
import com.github.database.rider.core.api.exporter.ExportDataSet
import com.github.database.rider.junit5.api.DBRider
import com.github.trks1970.EmailEntity
import com.github.trks1970.EmailRepository
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(
    classes = [
        H2JPAConfig::class,
    ]
)
@DBRider
@DBUnit(caseSensitiveTableNames = true, escapePattern = "\"?\"")
@Tag("datasetExport")
class DataGenerator {
    @Autowired
    lateinit var emailRepository: EmailRepository

    @Test
    @ExportDataSet(
        format = DataSetFormat.YML,
        outputName = "src/test/resources/datasets/data.yaml",
        dependentTables = true
    )

    fun exportData() {
        emailRepository.saveAll(
            listOf(
                EmailEntity(null, "lucifer@hell.org"),
                EmailEntity(null, "gabriel@heaven.org"),
                EmailEntity(null, "michael@heaven.org"),
                EmailEntity(null, "raphael@heaven.org")
            )
        )
    }
}