
#import
import pandas
from pandas import ExcelFile
from pandas import ExcelWriter

data = {

    'FirstName':["Sravani", "Anvitha", "hari"],
	'LastName':["manu", "krian", "krathi"],
	'Email':["sravani@example.com", "anvitha@example.com", "hari.krathi@example.com"],
	'PhoneNumber':["4537829158", "4892184058", "4528727830"]
}

dataframe = pandas.DataFrame(data)
	
# Print the dataframe
print(dataframe)

	
# Write the dataframe to a Excel file
writer = ExcelWriter('sample.xlsx')
dataframe.to_excel(writer, 'Sheet1', index = False)
 
# Commit data to the Excel file
writer.close()
