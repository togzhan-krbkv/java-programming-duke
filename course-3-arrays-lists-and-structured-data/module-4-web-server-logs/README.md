# Module 4: Web Server Logs. From Logs to Visits

In this module I wrote programs to parse web server log files and extract 
information about visitors: IP addresses, access times, request details, 
and status codes,  then used that data to answer questions about unique 
visitors and how often each one visited.

## Topics covered

- Reading and parsing structured log file entries into objects (`LogEntry`)
- Designing an algorithm and translating it into code, step by step
- Object equality and comparing values across records
- Using ArrayList to track unique values (IP addresses)
- Using HashMap to efficiently count and group repeated values (visit counts, IPs by day)

## Projects

- [ ReadingLogFiles](./ReadingLogFiles) — Reading Log Files. Parses a web 
  server log file into a list of `LogEntry` records and prints them.

- [ FindingUniqueIPAddresses](./FindingUniqueIPAddresses) — Finding 
  Unique IP Addresses. Builds on the reader to count unique visitor IPs, 
  filter entries by status code, find which IPs visited on a given day, 
  and count unique IPs within a status code range.

- [ CountingWebsiteVisits](./CountingWebsiteVisits) — Counting Website 
  Visits. Extends the analyzer with HashMap-based visit counting: total 
  visits per IP, which IP(s) visited the most, which day had the most 
  unique visitors, and which IP(s) visited most often on a specific day.
