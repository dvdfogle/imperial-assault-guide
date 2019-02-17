import json
import re
import requests
from bs4 import BeautifulSoup


def tag_with_text(tag, type, regex):
    return tag.name in type and tag.get_text() and re.search(regex, tag.get_text())


base_url = "https://imperial-assault.fandom.com"
homepage = base_url + "/wiki/Story_Mission"
page = requests.get(homepage)
soup = BeautifulSoup(page.text, "html.parser")

missions = []
# Easy to reuse for other sections
l = soup.article.find(lambda tag: tag_with_text(tag, ["h2","h3"], re.compile("(?i)expansions?:?")))

cards = []
for story in l.find_next_siblings("ul"):
    story_name = story.find_previous_sibling("h3").get_text().replace("Edit", "").strip()
    for mission in story.find_all("li"):
        card = {
            "name": mission.get_text().strip(),
            "story": story_name
            }
        cards.append(card)
    

with open("story.json", "w") as newfile:
    newfile.write(json.dumps(cards, indent=4, sort_keys=True))
