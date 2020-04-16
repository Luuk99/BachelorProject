#!/usr/bin/env python

# Constant for the search line
# Add your own search line here
search_line = "handler.postDelayed(runnable, "

# Constant for all different variables
# Add your own variables here
variables = ["low_frequency", "medium_frequency", "high_frequency"]

# Function that checks whether the line matches the line we are looking for
# Alter this function to suit your needs
def main(line, i):
   # Strips the line to check whether it starts with the search_line
   if (line.strip().startswith(search_line)):
      # If so, print the line with it's new variable
      print(search_line + variables[i] + ")")
      # Return an empty string to create space
      return ""
   else:
      # If not, return the line
      return line

# Function returns the variables
# DO NOT CHANGE OR REMOVE
def get_variables():
   return variables
