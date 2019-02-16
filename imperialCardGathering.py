import json
import re
import requests
from bs4 import BeautifulSoup

def tag_containing_text(tag, tag_type, regex):
	return tag.name in tag_type and (tag.find(string=regex) or re.search(regex, tag.string))

base_url = "https://imperial-assault.fandom.com/wiki"

homepage = requests.get("{}/Imperial_Assault_Wiki".format(base_url)).text
homesoup = BeautifulSoup(homepage)

target = "Imperial Player"
type_list = homesoup.find(lambda x: tag_containing_text(x, ["h3"], re.compile("Imperial Player"))).find_next_sibling("ul")
print(type_list)
for t in type_list.find_all("li"):
	a = t.find("a")
	type_name = a.get_text()
	type_page = requests.get("{}/{}".format(base_url, a.get("href")))
	type_soup = BeautifulSoup(type_page.text)
	collection = []
	collection_list = type_soup.find(lambda x: tag_containing_text(x, ["h2"], re.compile("Expansions?:?"))).find_next_siblings("ul")
	for objects in collection_list:
		for object in objects:
			new_thing = {}
			a = object.find("a")
			new_thing["name"] = a.get_text()
			details = requests.get("{}/{}".format(base_url, a.get("href")))
			detail_soup = BeautifulSoup(details.text)
			source = detail_soup.find("aside").find("a")
			new_thing["expansion"] = source.get_text()
			new_thing["card_list"] = []
			cardlist = source.parent.find_all("a")
			for a in cardlist:
				new_thing["card_list"].append(a.get_text())
			collection.append(new_thing)
	with open("ImperialAssault_{}.json".format(type_name), "w") as file:
		file.write(json.dumps(collection, indent=4, sort_keys=True))